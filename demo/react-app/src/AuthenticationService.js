import { setSelectionRange } from "@testing-library/user-event/dist/utils"

const API_URL = 'http://localhost:8080'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

class AuthenticationService {

    registerSuccessfulLogin( sessionId){
        console.log(sessionId)
        localStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, sessionId)
    }

    IsLoggedIn(){
        console.log("Getting from session storage")
        const user = localStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        console.log(user)
        if (user === null) return false
        return true
    }

    logout() {
        localStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    // executeBasicAuthenticationService(username, password) {
    //     return axios.get(`${API_URL}/basicauth`,
    //         { headers: { authorization: this.createBasicAuthToken(username, password) } })
    // }


    // createBasicAuthToken(username, password) {
    //     return 'Basic ' + window.btoa(username + ":" + password)
    // }

    // registerSuccessfulLogin(username, password) {
    //     //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
    //     //console.log('registerSuccessfulLogin')
    //     sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
    //     this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    // }

  

    // isUserLoggedIn() {
    //     let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    //     if (user === null) return false
    //     return true
    // }

    // getLoggedInUserName() {
    //     let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    //     if (user === null) return ''
    //     return user
    // }
}
export default new AuthenticationService()