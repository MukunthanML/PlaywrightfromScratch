.\CLI_Runner.bat codegen saucedemo.com
//Please run the following command to download new browsers:
.\CLI_Runner.bat codegen  install


Run your tests and generate test result data (ie, after running it will generate allure-results folder).
From the same project directory run, allure generate allure-results --clean -o allure-report in the command prompt
On successfull execution it will generate one more folder allure-report in your directory.
Open index.html file in FireFox to show the report.


allure open allure-report


allure serve allure-results