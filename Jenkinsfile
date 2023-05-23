node{ 
    def mavenHome
    def mavenCMD
    def docker
    def dockerCMD
    def tagName
    stage('Prepare Environment'){
        echo 'initialize all the variables'
        mavenHome = tool name: 'maven' , type: 'maven'
        mavenCMD = "${mavenHome}/bin/mvn"
        docker = tool name: 'docker' , type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        dockerCMD = "${docker}/bin/docker"
        tagName="1.0"
    }   
    stage('Git Code Checkout'){
        try{
            echo 'checkout the code from git repository'
            git branch: 'main', url: 'https://github.com/Vaibhav3101mankar/Banking-Finance-project.git'
        }
        catch(Exception e){
            echo 'Exception occurred in Git Code Checkout Stage'
            currentBuild.result = "FAILURE"
        emailext body: '''Dear All,
        The Jenkins job ${JOB_NAME} has been failed. Request you to please have a look at it immediately by clicking on the below link
        ${BUILD_URL}''', subject: 'Job ${JOB_NAME} ${BUILD_NUMBER}', to: 'vaibhav3101mankar@gmail'
    
        }
    }    
    stage('Build the Application'){
        echo "Cleaning... Compiling...Testing... Packaging..."
     // sh 'mvn clean package' 
        sh "${mavenCMD} clean package" 
    }
    stage('Publish Test Reports'){
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/var/lib/jenkins/workspace/Banking-Finance/target/surefire-reports', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
    }    
    stage('Containerize the Application'){
        echo 'Creating Docker image'
        sh "sudo ${dockerCMD} build -t vaibhav3101mankar/banking-finance:${tagName} ."
    }  
    stage('Pushing it to the DockerHub'){
        echo 'Pushing the docker image to DockerHub'
        withCredentials([string(credentialsId: 'dock-password', variable: 'dockerHubPassword')]) {
        sh "sudo ${dockerCMD} login -u vaibhav3101mankar -p ${dockerHubPassword}"
        sh "sudo ${dockerCMD} push vaibhav3101mankar/banking-finance:${tagName}"    
       }
    }   
    stage('Configure and Deploy to the Test-Server'){
        ansiblePlaybook become: true, credentialsId: 'ansible-key', disableHostKeyChecking: true, installation: 'Ansible', inventory: '/etc/ansible/hosts', playbook: 'ansible-playbook.yml'
    }
}
