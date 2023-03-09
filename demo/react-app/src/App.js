import React from 'react';
import './App.css';
import Login from './Component/Login';
import Profile from './Component/Profile';
import Manager from './Component/Manager';
import { Link, Route, Routes } from 'react-router-dom';
import AuthUtil from './AuthUtil';
import Home from './Component/Home';
import Navbar from './Component/Navbar'
import EventBus from './EventBus';

function App() {

  const [isLoggedIn, setIsLoggedIn] = React.useState(false)

  React.useEffect(()=>  setIsLoggedIn(AuthUtil.isLoggedIn()), [])

 const logout = () => {
    setIsLoggedIn(false)
    AuthUtil.logout();
    alert("You have been logged out!")
 }
 
 EventBus.on("login", () => {
  setIsLoggedIn(true)
});

  return (
    <>
    <div>
    <nav className='navbar'>
      <Navbar isLoggedIn={isLoggedIn} logout={logout}/>
    </nav>
    </div>
    <div className='container'>
    <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path="/login" element={<Login />}/>
        <Route path='/manager' element={<Manager />}/>
        <Route path='/profile' element={<Profile />}/>
    </Routes>
    </div>
    </>
  );
}

export default App;
