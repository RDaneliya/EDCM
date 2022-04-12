import React, {Component} from "react";

export default class Ccomponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    //.drinks | {mode: 'no-cors'})
    componentDidMount() {
        fetch("http://localhost:8080/api/commodities/overview", {
            mode: 'cors',
            headers: {'Access-Control-Allow-Origin': '*'}
        })  // http://localhost:8080/api/commodities/overview | https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const {error, isLoaded, items} = this.state;
        if (error) {
            return <p> Error {error.message}</p>
        } else if (!isLoaded) {
            return <p> Loading...</p>
        } else { // commodityName | strDrink
            return (
                <ul>
                    {items.map(item => (
                        <li key={item.name}>
                            {item.commodityName}
                        </li>
                    ))}
                </ul>
            )
        }
    }
}