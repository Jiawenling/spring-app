import React from 'react'

function Manager() {
    const [text, setText] = React.useState("")
    React.useEffect(()=> fetch("http://localhost:8080/api/manager",
        {
            method: "GET",
            headers: {Accept: 'application/json', 'Content-Type': 'application/json'}
        })
        .then(v=> setText(v)), [])
      return (
        <div>
            <h1>{text}</h1>
        </div>
      )
    }


export default Manager