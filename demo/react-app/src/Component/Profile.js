import React from 'react'
import { Link } from 'react-router-dom'
import AuthUtil from '../AuthUtil';

function Profile() {
  const currentUser = AuthUtil.getCurrentUser();
  if(currentUser){
    return (
      <div>
         <h1>Hi {currentUser.name}</h1>
        <div> 
        <p>Username: {currentUser.username}</p>
        <p>Role: </p>
          {currentUser.roles.map((item, i)=><p key={i}>{item}</p>)}
        </div>
        {currentUser.roles.includes("ROLE_MANAGER") && <h3>You can access <Link to="/manager">manager's page</Link></h3>}
      </div>
    )}

   return(
      <div>
        You are not logged in. Click <Link to={"/login"}>here</Link> to login.
      </div>
   )

}  

export default Profile
