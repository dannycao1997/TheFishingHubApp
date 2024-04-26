import React from 'react';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <nav>
            <ul>
                <li><Link to="/feed">Community Feed</Link></li>
                <li><Link to="/species">Fish Species</Link></li>
                <li><Link to="/map">Map</Link></li>
                <li><Link to="/weather">Weather</Link></li>
                <li><Link to="/logbook">Catch Logbook</Link></li>
            </ul>
        </nav>
    );
}

export default Navigation;
