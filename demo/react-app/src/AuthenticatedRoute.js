import React, { Component } from 'react'
import { Outlet, Navigate } from 'react-router-dom'
import AuthenticationService from './AuthenticationService';

function AuthenticatedRoute() {
    const isLoggedIn = AuthenticationService.IsLoggedIn()
    console.log(`Is logged in: ${isLoggedIn}`)
        if (isLoggedIn) {
            <Outlet/> 
        } else {
            return <Navigate to="/login" />
        }

    }

export default AuthenticatedRoute