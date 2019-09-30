#!/bin/bash

cd Desktop
timestamp = 'date "+%Y-%m-%d %H:%M:%S"`
sudo fswebcam -r 320x240 --flip v --no-banner + "images/" + timestamp + ".jpg"
echo $timestamp
