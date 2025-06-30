#!/bin/bash

set -e  # Exit on error

echo "🛑 Stopping all running Docker containers..."
docker stop $(docker ps -q) 2>/dev/null || echo "No running containers."

echo "🧹 Removing all Docker containers..."
docker rm $(docker ps -aq) 2>/dev/null || echo "No containers to remove."

echo "🧼 Removing all Docker images..."
docker rmi -f $(docker images -q) 2>/dev/null || echo "No images to remove."

echo "🐘 (Optional) Pulling PostgreSQL image (or skip if handled by Kubernetes)..."
# docker pull postgres  # Uncomment if needed

echo "📥 Pulling your app image from Docker Hub..."
docker pull maiconf/gerg2008

echo "🚀 Applying Kubernetes services..."
kubectl apply -f services.yml

echo "📦 Applying Kubernetes deployments..."
kubectl apply -f deployments.yml

echo "✅ All containers deployed to Kubernetes!"


