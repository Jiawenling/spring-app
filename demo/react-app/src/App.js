import logo from './logo.svg';
import './App.css';
import Login from './Login';
import Profile from './Profile';
import Manager from './Manager';
import { Route, Routes } from 'react-router-dom';
import Board from './Board';
import AuthenticatedRoute from './AuthenticatedRoute';
function App() {
  return (
    <Routes>
        <Route exact path="/" element={<Login />}/>
        <Route path="/login" element={<Login />}/>
        <Route path='/manager' element={<Manager />}/>
        <Route path='/profile' element={<Profile />}/>
        {/* <Route  element={<AuthenticatedRoute />}>
          <Route path='/manager' element={<Manager />}/>
          <Route path='/profile' element={<Profile />}/>
        </Route> */}
    </Routes>
  );
}

export default App;
