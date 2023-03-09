import React from 'react'
import AuthUtil from '../AuthUtil'

function Manager() {
  const [text, setText] = React.useState("")
  React.useEffect(()=> {
  AuthUtil.getRequest("/api/manager")
  .then(response=> {
    console.log(response)
    setText(response)
  })
  .catch(error=> 
    {
      if(error.status === 401) {setText("You are not authorised to view this page")}
      else setText("Something went wrong")
    })
},[])
      return (
        <div>
            <h1>{text}</h1>
        </div>
      )
    }


export default Manager