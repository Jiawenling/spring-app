import React, { Component } from 'react'
import { Outlet, Navigate } from 'react-router-dom'
import AuthenticationService from './AuthenticationService';

function AuthenticatedRoute({Component}) {
        console.log(`Authenticating:${Component}`)
        if (AuthenticationService.IsLoggedIn()) {
            <Component/> 
        } else {
            return <Navigate to="/login" />
        }

    }

export default AuthenticatedRoute