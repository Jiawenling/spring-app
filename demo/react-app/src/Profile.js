import React from 'react'
import { Link } from 'react-router-dom'

function Profile() {
const [profile, setProfile] = React.useState({name:"", username:"", role:"", privateLink:""})
React.useEffect(()=> fetch("http://localhost:8080/api/profile",
    {
        method: "GET",
        headers: {Accept: 'application/json', 'Content-Type': 'application/json'}
    })
    .then(v=> 
      {
        console.log(v)
        setProfile(v)
      })
    .catch(e=> alert("Something went wrong!"))
    , [])
  return (
    <div>
        <h1>Hi {profile.name}</h1>
        <h3>Role: {profile.role}</h3>
        {profile.privateLink!==""&& <h3>You can access <Link to="/manager">manager's page</Link></h3>}
    </div>
  )
}

export default Profile
