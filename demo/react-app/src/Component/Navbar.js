import React from 'react'
import { Link } from 'react-router-dom'

function Navbar({isLoggedIn, logout}) {
  return (
    <ul>
    <li><Link className='nav-item' to={"/"}>Demo Application</Link></li>
    {!isLoggedIn &&<li>
      <Link to={"/login"}>Login</Link>
    </li>}
    {isLoggedIn &&<li>
      <Link to={"/profile"}>Profile</Link>
    </li>}
    {isLoggedIn &&<li>
      <Link onClick={logout} to={"/login"}>Logout</Link>
    </li>}
  </ul>
  )
}

export default Navbar