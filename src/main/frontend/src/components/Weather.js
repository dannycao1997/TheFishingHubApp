import React, { useState, useEffect } from 'react';

function Weather() {
    const [weather, setWeather] = useState('');

    useEffect(() => {
        // Fetch weather data from an API
    }, []);

    return (
        <div>
            <h1>Weather Information</h1>
            <p>{weather}</p>
        </div>
    );
}

export default Weather;
