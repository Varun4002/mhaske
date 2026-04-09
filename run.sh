#!/bin/bash
cd "$(dirname "$0")"

echo "=========================================="
echo "Starting Online Examination System..."
echo "=========================================="
echo ""
echo "Open in your browser:"
if command -v ipconfig &> /dev/null; then
    IP=$(ipconfig getifaddr en0)
    echo "http://$IP:8080"
else
    echo "http://localhost:8080"
fi
echo ""
echo "Press Ctrl+C to stop"
echo "=========================================="
echo ""

mvn spring-boot:run