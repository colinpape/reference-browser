/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.reference.browser.search

import mozilla.components.browser.state.search.SearchEngine
import mozilla.components.browser.state.state.SearchState
import mozilla.components.feature.search.SearchEngineSource
import mozilla.components.feature.search.ext.createSearchEngine

/**
 * Custom search engine for Savedd (Christian search engine).
 *
 * URL template: https://savedd.com/?q={searchTerms}
 * Confirmed from Savedd web app (Index.tsx uses ?q= query param).
 */
object SaveddSearchEngine {
    const val ID = "savedd"
    const val NAME = "Savedd"
    const val SEARCH_TEMPLATE = "https://savedd.com/?q={searchTerms}"

    fun create(): SearchEngine =
        createSearchEngine(
            name = NAME,
            url = SEARCH_TEMPLATE,
            icon = null, // Can be added later as a drawable
            isGeneral = true,
        ).copy(id = ID)
}
