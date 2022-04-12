import React from 'react';
import logo from './logo.svg';
import './App.css';
import Commodities from "./Commodities";
import {
    Router,
    Routes,
    Route,
    Link,
} from 'react-router-dom';

export const App = () => (
    <div className="App">
        <header className="App-header">
            <Link to="/" >Elite: Dangerous Commodity Monitoring</Link>
            <div>
                <Link to="/API" >API</Link>
                <Link to="/Bot" >Bot</Link>
                <Link to="/Help" >Help</Link>
            </div>
        </header>

        <main>
            <Routes>
                <Route path='/' element={<Home/>} />
                <Route path='/API' element={<API/>} />
                <Route path='/Bot' element={<Bot/>} />
                <Route path='/Help' element={<Help/>} />
                <Route path='/Commodities' element={<Commodities/>} />
            </Routes>
        </main>
    </div>
);

function Home() {
    return (
        <div className="mainblock">
            <Link to="/Stations" className="blue_block">
                <h3>Stations</h3>
                <p>Browse the universe! Jump to any stations or search
                    by many properties. You can even find the ship you
                    wanna buy!</p>
            </Link>
            <Link to="/Commodities" className="yellow_block">
                <h3>Commodities</h3>
                <p>Browse commodities, compare and sort by numbers and
                    go into detail to find out who sells or buys the best!</p>
            </Link>
            <Link to="/Find_Commodity" className="gray_block">
                <h3>Find Commodity</h3>
                <p>Mission problems? Can't find a station that sells you
                    that stuff? Then the Commodity Finder is what you need!</p>
            </Link>
        </div>
    );
}

const API = () => <h2>API</h2>;

const Bot = () => <h2>Bot</h2>;

const Help = () => <h2>Help</h2>;