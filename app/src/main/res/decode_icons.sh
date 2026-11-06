#!/bin/bash
# Decode Savedd launcher icons from base64 into the mipmap folders
cd "$(dirname "$0")" || exit 1

for size in mdpi hdpi xhdpi xxhdpi xxxhdpi; do
  if [ -f "mipmap-$size/ic_launcher.b64" ]; then
    base64 -d "mipmap-$size/ic_launcher.b64" > "mipmap-$size/ic_launcher.png"
    echo "Decoded mipmap-$size/ic_launcher.png"
  fi
done

if [ -f "play_store_icon.b64" ]; then
  base64 -d play_store_icon.b64 > play_store_icon.png
  echo "Decoded play_store_icon.png (512x512)"
fi

echo "Done. The ic_launcher.png files are ready for the build."
echo "Note: the project uses adaptive icons. You may want to replace ic_icon_background.png or simplify to these launcher icons."
