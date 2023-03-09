  const login = (currUsername, currPassword) => {
    return fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {Accept: 'application/json', 'Content-Type': 'application/json'},
            body: JSON.stringify({username:currUsername, password:currPassword}),
    })
    .then(response=> 
    { if(response.ok) return response.json()
      throw response})
  }

  const isLoggedIn = () => localStorage.getItem("user")!==null

  const logout = () => {
    localStorage.removeItem("user");
  };
  
  const getRequest = (endpoint)=> {
    return fetch(`http://localhost:8080${endpoint}`, {
            method: "GET",
            headers: {Authorization: `${authHeader()}`}
    }).then(response=> 
      { 
        if(response.ok) return response.text()
        throw response
      })
  }

  const setCurrentUser= (user) => {
    localStorage.setItem("user", JSON.stringify(user))
  }

  const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
  };

  const authHeader =() =>{
    const user = getCurrentUser()
    if (user && user.token) return `Bearer ${user.token}` 
    else return "";
  }
  
  const AuthUtil = {
    login,
    isLoggedIn,
    logout,
    getCurrentUser,
    setCurrentUser,
    getRequest
  };
  
  export default AuthUtil;
  