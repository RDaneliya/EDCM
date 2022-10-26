import { Grid, Typography } from '@mui/material'
import React from 'react'

const OneCommodity = ({ commodity }) => {
    const gridSx = {
        transition:'transform 0.1s',
        '&:hover' : {
            transform:'scale(1.03)'
        }
    }
    return (
        <Grid container borderBottom='2px solid gray' sx={gridSx}>
            <Grid item xs={4}>
                <Typography sx={{ typography: { sm: 'h6', xs: 'body2' } }} >{commodity.commodityName}</Typography>
            </Grid>
            <Grid item xs={8}>
                <Grid container>
                    <Grid item xs={4}>
                        <Typography sx={{ typography: { sm: 'h6', xs: 'body2' } }} textAlign='center'>{commodity.minBuyPrice}</Typography>
                    </Grid>
                    <Grid item xs={4}>
                        <Typography sx={{ typography: { sm: 'h6', xs: 'body2' } }} textAlign='center'>{commodity.maxSellPrice}</Typography>
                    </Grid>
                    <Grid item xs={4}>
                        <Typography sx={{ typography: { sm: 'h6', xs: 'body2' } }} textAlign='center'>{commodity.maxSellPrice - commodity.minBuyPrice}</Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Grid>
    )
}

export default OneCommodity