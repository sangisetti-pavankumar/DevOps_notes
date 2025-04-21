import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()
def hudsonRealm = new HudsonPrivateSecurityRealm(false)

// Check if admin user already exists
def adminUsername = System.getenv("JENKINS_ADMIN_USERNAME") ?: "admin"
def adminPassword = System.getenv("JENKINS_ADMIN_PASSWORD") ?: "admin123"

if (!hudsonRealm.getAllUsers().any { it.id == adminUsername }) {
    hudsonRealm.createAccount(adminUsername, adminPassword)
}

instance.setSecurityRealm(hudsonRealm)

// Set up authorization strategy
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()