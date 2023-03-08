const login = (username, password) => {
    return axios
      .post(API_URL + "signin", {
        username,
        password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
  
        return response.data;
      });
  };
  
  const isLoggedIn = () => localStorage.getItem("user")!==null

  const logout = () => {
    localStorage.removeItem("user");
  };
  
  const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
  };
  
  const AuthUtil = {
    register,
    login,
    isLoggedIn,
    logout,
    getCurrentUser,
  };
  
  export default AuthUtil;
  