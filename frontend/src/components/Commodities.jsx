import React, { Component } from "react";
import { Paper, Grid, Typography, Button, CircularProgress } from '@mui/material'

import OneCommodity from './OneCommodity'

export default class Commodities extends Component {
    constructor(props) {
        super(props);
        this.update = this.update.bind(this);
        this.state = {
            error: null,
            isLoaded: false,
            items: [],
            currentSort: (a, b) => (b.maxSellPrice - b.minBuyPrice) - (a.maxSellPrice - a.minBuyPrice),
            currentSortId: 0,
        };
    }

    update() {
        fetch("http://localhost:8080/api/commodities/overview", {
            mode: 'cors',
            headers: { 'Access-Control-Allow-Origin': '*' }
        })
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result.sort(this.state.currentSort)
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

    componentDidMount() {
        this.update()
    }

    render() {
        const { error, isLoaded, items, currentSortId } = this.state;
        const handleBuy = () => {
            const sortBuyUp = (a, b) => b.minBuyPrice - a.minBuyPrice
            const sortBuyDown = (a, b) => a.minBuyPrice - b.minBuyPrice

            if (currentSortId === 4) {
                this.setState({ ...this.state, currentSort: sortBuyDown, currentSortId: 5 })
                this.update()
                return;
            }
            this.setState({ ...this.state, currentSort: sortBuyUp, currentSortId: 4 })
            this.update()

        }

        const handleSell = () => {
            const sortSellUp = (a, b) => b.maxSellPrice - a.maxSellPrice
            const sortSellDown = (a, b) => a.maxSellPrice - b.maxSellPrice

            if (currentSortId === 2) {
                this.setState({ ...this.state, currentSort: sortSellDown, currentSortId: 3 })
                this.update()
                return;
            }
            this.setState({ ...this.state, currentSort: sortSellUp, currentSortId: 2 })
            this.update()

        }

        const handleProfit = () => {
            const sortProfitUp = (a, b) => (b.maxSellPrice - b.minBuyPrice) - (a.maxSellPrice - a.minBuyPrice)
            const sortProfitDown = (a, b) => (a.maxSellPrice - a.minBuyPrice) - (b.maxSellPrice - b.minBuyPrice)

            if (currentSortId === 0) {
                this.setState({ ...this.state, currentSort: sortProfitDown, currentSortId: 1 })
                this.update()
                return;
            }
            this.setState({ ...this.state, currentSort: sortProfitUp, currentSortId: 0 })
            this.update()

        }

        if (error) {
            return <p> Error {error.message}</p>
        } else if (!isLoaded) {
            return <div style={{position:'absolute', left:'50%', top:'50%', transform:'translate(-50%, -50%)'}}>
                <CircularProgress size="10rem"/>
                </div>
        } else {
            return (
                <Paper sx={{ width: '80vw', m: '20px auto', p: '40px' }} elevation={3}>
                    <Button variant='contained' onClick={() => this.update()}>Refresh</Button>
                    <Grid container >
                        <Grid item xs={4}>
                            <Typography color='primary'  sx={{ typography: { sm: 'h6', xs: 'body2' } }} >Name</Typography>
                        </Grid>
                        <Grid item xs={8}>
                            <Grid container>
                                <Grid item xs={4}>
                                    <Typography color='primary' onClick={handleBuy} sx={{cursor:'pointer',  typography: {sm: 'h6', xs: 'body2' } }} textAlign='center'>Buy</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                    <Typography color='primary'  onClick={handleSell} sx={{cursor:'pointer',  typography: { sm: 'h6', xs: 'body2' } }} textAlign='center'>Sell</Typography>
                                </Grid>
                                <Grid item xs={4}>
                                    <Typography color='primary'  onClick={handleProfit} sx={{cursor:'pointer',  typography: { sm: 'h6', xs: 'body2' } }} textAlign='center'>Profit</Typography>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                    {items.map(item => (<OneCommodity commodity={item} key={item.commodityId} />))}
                </Paper>
            )
        }
    }
}
