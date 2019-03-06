call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runwebbrowser
echo.
echo runcrud.bat file has errors - breaking work
goto fail

:runwebbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished
