#!/bin/bash

set -e  # Exit on error

echo "🛑 Stopping all running Docker containers..."
docker stop $(docker ps -q) 2>/dev/null || echo "No running containers."

echo "🧹 Removing all Docker containers..."
docker rm $(docker ps -aq) 2>/dev/null || echo "No containers to remove."

echo "🧼 Removing all Docker images..."
docker rmi -f $(docker images -q) 2>/dev/null || echo "No images to remove."

echo "🐘 Building PostgreSQL image from scratch (if needed)..."
# You can skip this if using official postgres image. Otherwise, build a custom one.
# docker build -t custom-postgres ./path/to/your/Dockerfile

echo "⚙️ Building Java application image from parent directory..."
docker build -t gerg-app ../

echo "🚀 Applying Kubernetes services..."
kubectl apply -f services.yml

echo "📦 Applying Kubernetes deployments..."
kubectl apply -f deployments.yml

echo "✅ All containers deployed to Kubernetes!"

