#!/usr/bin/env bash
git add .
git commit -m 'add code'
git pull
git push
git checkout main
git pull
sleep 1
git merge dev
git pull
git push
git checkout dev
