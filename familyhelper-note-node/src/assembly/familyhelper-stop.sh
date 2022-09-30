#!/bin/sh
# 程序的根目录
basedir=/usr/local/familyhelper-note

PID=$(cat "$basedir/familyhelper.pid")
kill "$PID"
