pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2"
    }
//     triggers {
//         cron('0 8 * * *')
//     }
    parameters {
        gitParameter ( branch: '',
        branchFilter: 'origin/(.*)',
        defaultValue: 'master',
        description: '',
        name: 'BRANCH',
        quickFilterEnabled: true,
        selectedValue: 'NONE',
        sortMode: 'NONE',
        tagFilter: '*',
        type: 'PT_BRANCH' )
    }

    stages {
        stage('Prepare Selenoid: starting containers') {
            steps {
                bat "docker pull selenoid/$BROWSER"
                bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid start"
                bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid-ui start"
                bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid status"
                bat "curl http://localhost:4444/status"
            }
        }
        stage('UI tests') {

            steps {

                script {

                    try {

                        // Get some code from a GitHub repository
                        git branch: "${params.BRANCH}",  url: 'https://github.com/Maxim-Kazliakouski/UI_API.git'

                        //withCredentials ([
                        //    string(credentialsId: 'qase_token',
                        //variable: 'TOKEN_CREDENTIALS'),
                        //    string(
                        //        credentialsId: 'qase_password',
                        //        variable: 'PASSWORD_CREDENTIALS')
                        //])


                            // Run Maven on a Unix agent.
                            bat "mvn clean -Dsurefire.suiteXmlFiles=src/test/resources/APItests.xml test"
                            //-P UI -Dbrowser=$BROWSER \
                            //-DbrowserVersion=$VERSION \
                            //-DvideoTestRecord=$VIDEO_TEST_RECORD \
                            //-Dheadless=$HEADLESS \
                            //-Dqase.username=$USERNAME \
                            //-Dqase.password=$PASSWORD_CREDENTIALS \
                            //-Dtoken=$TOKEN_CREDENTIALS \
                            //-DtestRun=$TESTRUN \
                            //-DcodeProject=$CODEPROJECT

                    } catch (Exception error)
                    {
                        unstable('Testing failed')
                    }
                }
            }

            // To run Maven on a Windows agent, use
            // bat "mvn -Dmaven.test.failure.ignore=true clean package"

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Stopping and deleting containers') {
            steps {
                script {
                    bat 'docker stop selenoid'
                    bat 'docker rm selenoid'
                    bat 'docker stop selenoid-ui'
                    bat 'docker rm selenoid-ui'
                }
            }
        }

        stage('Generating Allure report for public nginx') {
            steps{
                script{
                    generateAllure()
                    labelledShell(label: "Move allure results to nginx public directory", script: '''
                    timestamp=$(date +%F_%T)
                    folder=${BRANCH}_allure_${timestamp}
                    mv allure-report ${folder}
                    bat "docker cp C://ProgramData//Jenkins//.jenkins//workspace//UI_API//allure-report// nginx-server:/var/www/html"
                    //cp -R ${folder} /var/www/html/
                    echo "http://localhost:5555/${folder}"
                    ''')
            }
        }
    }
}

def generateAllure() {
    allure([
    includeProperties: false,
    jdk: '',
    properties: [],
    reportBuildPolicy: 'ALWAYS',
    results: [[path: 'target/allure-results']]
    ])}