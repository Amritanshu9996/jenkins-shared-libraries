// vars/code_checkout.groovy

def call(Map config = [:]) {
    // Defaults if not provided
    def branch = config.get('branch', 'main')
    def repo   = config.get('repo', 'https://github.com/your-org/your-repo.git')
    def credentialsId = config.get('credentialsId', '')

    echo "Checking out code from ${repo}, branch: ${branch}"

    if (credentialsId) {
        checkout([
            $class: 'GitSCM',
            branches: [[name: branch]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[$class: 'CleanBeforeCheckout']],
            userRemoteConfigs: [[
                url: repo,
                credentialsId: credentialsId
            ]]
        ])
    } else {
        checkout([
            $class: 'GitSCM',
            branches: [[name: branch]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[$class: 'CleanBeforeCheckout']],
            userRemoteConfigs: [[url: repo]]
        ])
    }
}
