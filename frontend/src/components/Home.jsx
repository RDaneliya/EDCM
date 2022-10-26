import { useTheme } from '@emotion/react'
import { Typography, Paper, Grid } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router'

const Home = () => {
    const theme = useTheme()
    const paperSx = {
        p: '20px',
        width: '80vw',
        cursor: 'pointer',
        transition: 'transform 0.2s',
        '&:hover': {
            background: theme.palette.secondary.dark,
            transform: 'scale(1.02)'
        },
        '&:active': {
            transform: 'scale(0.98)'
        },
    }

    const navigate = useNavigate()

    return (
        <Grid container spacing={4} direction="column" alignItems="center" mt={6} >
            <Grid item onClick={() => { navigate('/Stations') }} >
                <Paper elevation={3} sx={paperSx} >
                    <Typography variant="h3">Stations</Typography>
                    <Typography variant="body1">Browse the universe! Jump to any stations or search
                        by many properties. You can even find the ship you
                        wanna buy!</Typography>
                </Paper>
            </Grid>
            <Grid item onClick={() => { navigate('/Commodities') }}>
                <Paper elevation={3} sx={paperSx}>
                    <Typography variant="h3">Commodities</Typography>
                    <Typography variant="body1">Browse commodities, compare and sort by numbers and
                        go into detail to find out who sells or buys the best!</Typography>
                </Paper>
            </Grid>
            <Grid item onClick={() => { navigate('/Find_Commodity') }}>
                <Paper elevation={3} sx={paperSx}>
                    <Typography variant="h3">Find Commodity</Typography>
                    <Typography variant="body1">Mission problems? Can't find a station that sells you
                        that stuff? Then the Commodity Finder is what you need!</Typography>
                </Paper>
            </Grid>
        </Grid>
    )
}

export default Home