import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FishSpecies = () => {
    const [species, setSpecies] = useState([]);

    useEffect(() => {
        const fetchSpecies = async () => {
            try {
                const response = await axios.get('/api/species'); // Adjust the API endpoint as needed
                setSpecies(response.data);
            } catch (error) {
                console.error('Error fetching species:', error);
            }
        };

        fetchSpecies();
    }, []);

    return (
        <div className="container mt-4">
            <h2>Fish Species Database</h2>
            <ul className="list-group">
                {species.map(specie => (
                    <li key={specie.id} className="list-group-item">
                        {specie.name} - {specie.description}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default FishSpecies;
