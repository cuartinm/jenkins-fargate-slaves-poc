pipeline {
  agent none

  stages {
       stage('PublishAndTests') {
          environment {
              STAGE='prod'
          }
          agent {
            label 'build-python36'
          }
      }
      steps {
        sh 'java -version'
      }
    }
  }
pipeline {
  agent none

  stages {
    stage('Test') {
        agent {
            ecs {
                inheritFrom 'label-of-my-preconfigured-template'
                cpu 2048
                memory 4096
                image '$AWS_ACCOUNT.dkr.ecr.$AWS_REGION.amazonaws.com/jenkins/java8:2019.7.29-1'
                logDriver 'fluentd'
                logDriverOptions([[name: 'foo', value:'bar'], [name: 'bar', value: 'foo']])
                portMappings([[containerPort: 22, hostPort: 22, protocol: 'tcp'], [containerPort: 443, hostPort: 443, protocol: 'tcp']])
            }
        }
        steps {
            sh 'echo hello'
        }
    }
  }
}