#!/usr/bin/env bash
set -e

# Variablen
APP_NAME="notesplash"
INSTALL_DIR="$HOME/.local/$APP_NAME"
BIN_DIR="/usr/local/bin"
REPO="dein-user/NoteSplash"  # ersetze durch deinen GitHub-Namen
VERSION="v1.0.0"             # aktuelle Version deines Releases
ZIP_NAME="$APP_NAME.zip"

# 1. Temporäres Verzeichnis erstellen
TMP_DIR=$(mktemp -d)
cd "$TMP_DIR"

# 2. ZIP von GitHub Release herunterladen
echo "Lade Notesplash $VERSION herunter..."
curl -L -o "$ZIP_NAME" "https://github.com/$REPO/releases/download/$VERSION/$ZIP_NAME"

# 3. Installationsverzeichnis erstellen
mkdir -p "$INSTALL_DIR"
echo "Installiere in $INSTALL_DIR..."
unzip -q "$ZIP_NAME" -d "$INSTALL_DIR"

# 4. Symlink ins PATH setzen
if [ -w "$BIN_DIR" ]; then
    ln -sf "$INSTALL_DIR/bin/$APP_NAME" "$BIN_DIR/$APP_NAME"
    echo "Symlink erstellt: $APP_NAME → $INSTALL_DIR/bin/$APP_NAME"
else
    echo "WARNUNG: Kein Schreibzugriff auf $BIN_DIR, bitte Symlink manuell setzen:"
    echo "ln -s $INSTALL_DIR/bin/$APP_NAME /usr/local/bin/$APP_NAME"
fi

# 5. Bereinigung
cd ~
rm -rf "$TMP_DIR"

echo "Installation abgeschlossen! Du kannst jetzt einfach 'notesplash' eingeben."
