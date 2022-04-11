import logo from './logo.svg';
import './App.css';
import Ccomponent from "./Ccomponent";

function App() {

  return (
    <div className="App">
      <header className="App-header">
          <div>Elite: Dangerous Commodity Monitoring</div>
          <div>
            <a href="#" className="right">API</a>
            <a href="#" className="right">Bot</a>
            <a href="#" className="right">Help</a>
          </div>
      </header>

        <div className="mainblock">
            <block className="blue_block">
                <h3>Stations</h3>
                <p>Browse the universe! Jump to any stations or search
                    by many properties. You can even find the ship you
                    wanna buy!</p>
            </block>
            <block className="yellow_block">
                <h3>Commodities</h3>
                <p>Browse commodities, compare and sort by numbers and
                    go into detail to find out who sells or buys the best!</p>
            </block>
            <block className="gray_block">
                <h3>Find Commodity</h3>
                <p>Mission problems? Can't find a station that sells you
                    that stuff? Then the Commodity Finder is what you need!</p>
            </block>
        </div>

    </div>
  );
}

export default App;
