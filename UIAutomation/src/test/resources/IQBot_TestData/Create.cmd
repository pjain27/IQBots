@echo off
C:
cd C:\IQBot_TestData\CreateInstanceDoc
dir /b /a-d | findstr /v /i ".dat .bat .atmx .bdic .cmd .classpath .gitignore .project .iml .json .xml .md .txt"
