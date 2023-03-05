import React from 'react'

function Manager() {
    const [text, setText] = React.useState("")
    React.useEffect(()=> fetch("/api/manager",
        {
            method: "GET"
        })
        .then(v=> setText(v)), [])
      return (
        <div>
            <h1>{text}</h1>
        </div>
      )
    }


export default Manager