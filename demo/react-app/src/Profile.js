import React from 'react'

function Profile() {
const [profile, setProfile] = React.useState({name:"", username:"", role:"", privateLink:""})
React.useEffect(()=> fetch("/api/profile",
    {
        method: "GET"
    })
    .then(v=> setProfile(v)), [])
  return (
    <div>
        <h1>Hi {profile.name}</h1>
        <h3>Role: {profile.role}</h3>
        {profile.privateLink!==""&& <h3>Because you are a manager, you can access <a>{profile.privateLink}</a></h3>}
    </div>
  )
}

export default Profile