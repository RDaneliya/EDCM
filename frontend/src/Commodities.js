import React, {Component} from "react";

export default class Commodities extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    componentDidMount() {
        this.timer = setInterval(()=> {
            fetch("http://localhost:8080/api/commodities/overview", {
                mode: 'cors',
                headers: {'Access-Control-Allow-Origin': '*'}
            })
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
        },1000)
    }

    render() {
        const {error, isLoaded, items} = this.state;
        if (error) {
            return <p> Error {error.message}</p>
        } else if (!isLoaded) {
            return <p> Loading...</p>
        } else {
            return (
                <table>
                    <tr>
                        <td bgcolor="#b2b2b7" width="35%"><b>Commodity Name</b></td>
                        <td bgcolor="#b2b2b7" width="15%"><b>Buy Price</b></td>
                        <td bgcolor="#b2b2b7" width="15%"><b>Sell Price</b></td>
                        <td bgcolor="#b2b2b7" width="15%"><b>Profit</b></td>
                    </tr>
                    <td>
                        {items.map(item => (
                        <tr>{item.commodityName}</tr>))}</td>
                    <td>{items.map(item => (
                        <tr>{item.minBuyPrice} </tr>))}</td>
                    <td>{items.map(item => (
                        <tr>{item.maxSellPrice}</tr>))}</td>
                    <td>{items.map(item => (
                        <tr>{item.maxSellPrice - item.minBuyPrice}</tr>))}</td>
                </table>
            )
        }
    }
}
