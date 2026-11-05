/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.search

import mozilla.components.browser.state.action.BrowserAction
import mozilla.components.browser.state.action.SearchAction
import mozilla.components.browser.state.state.BrowserState
import mozilla.components.feature.search.middleware.SearchMiddleware
import mozilla.components.lib.state.Middleware
import mozilla.components.lib.state.MiddlewareContext

/**
 * Middleware that ensures Savedd is the default search engine on startup.
 *
 * Wraps the standard SearchMiddleware and, after it initializes, selects
 * the Savedd engine as the selected/default engine.
 */
class SaveddSearchMiddleware(
    private val context: android.content.Context,
) : Middleware<BrowserState, BrowserAction> {

    private val searchMiddleware = SearchMiddleware(context)

    override fun invoke(
        middlewareContext: MiddlewareContext<BrowserState, BrowserAction>,
        next: (BrowserAction) -> Unit,
        action: BrowserAction,
    ) {
        // Let the standard middleware run first
        searchMiddleware.invoke(middlewareContext, next, action)

        // After search engines are loaded, force Savedd as selected
        if (action is SearchAction.SetSearchEnginesAction ||
            action is SearchAction.ApplicationSearchEnginesLoaded
        ) {
            val state = middlewareContext.state
            val savedd = state.search.availableSearchEngines.find { it.id == SaveddSearchEngine.ID }
                ?: SaveddSearchEngine.create()

            // Ensure it's in the list and selected
            if (state.search.selectedOrDefaultSearchEngine?.id != SaveddSearchEngine.ID) {
                middlewareContext.dispatch(
                    SearchAction.SelectSearchEngineAction(
                        searchEngineId = SaveddSearchEngine.ID,
                        source = mozilla.components.feature.search.SearchEngineSource.User,
                    ),
                )
            }
        }
    }
}
