import React from 'react';
import CommunityFeed from './components/CommunityFeed';
import 'bootstrap/dist/css/bootstrap.min.css';
import FishSpecies from './components/FishSpecies';
import InteractiveMap from "./components/InteractiveMap";



function App() {
  return (
      <div className="App">
        <CommunityFeed/>
          <FishSpecies/>
          <InteractiveMap/>
      </div>
  );
}

export default App;
