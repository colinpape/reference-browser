# Savedd App Icon

Master icon: gold Christian cross inside a magnifying glass on dark burgundy (#2C0812).

## Quick start

The launcher icons are committed as base64 so they can be stored in Git. To decode them into real PNGs:

```bash
cd app/src/main/res
bash decode_icons.sh
```

This creates:

- `mipmap-mdpi/ic_launcher.png` (48x48)
- `mipmap-hdpi/ic_launcher.png` (72x72)
- `mipmap-xhdpi/ic_launcher.png` (96x96)
- `mipmap-xxhdpi/ic_launcher.png` (144x144)
- `mipmap-xxxhdpi/ic_launcher.png` (192x192)

A 512x512 Play Store icon can also be decoded if `play_store_icon.b64` is present.

The project currently uses adaptive icons (`ic_icon_background.png` + `ic_icon_foreground.xml`). After decoding, you can replace the background PNGs or switch the launcher to use these simple `ic_launcher.png` files.

The master 1024x1024 source is in the iOS repo (Assets/AppIcon-1024.b64) and can be decoded there.
