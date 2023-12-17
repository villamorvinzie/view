#!/bin/bash

echo "[install-hooks] installing hooks to your local."

# copy pre-commit hook
cp ./scripts/hooks/pre-commit ./.git/hooks/