import { useTheme } from '@emotion/react'
import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material'
import { useNavigate } from "react-router-dom"

const Header = () => {
    const navigate = useNavigate()
    const theme = useTheme()
    const buttonSx = {
        width: '180px',
        m: '0 10px',
        transition: 'transform 0.2s',
        '&:hover': {
            background: theme.palette.secondary.dark,
            transform: 'scale(1.02)'
        },
        '&:active': {
            transform: 'scale(0.98)'
        },
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar sx={{ display: 'flex', flexDirection: 'row', alignItems: 'center' }}>
                <Toolbar>
                    <Typography variant="h5">Elite: Dangerous Commodity Monitoring</Typography>
                </Toolbar>
                <Button variant="contained" sx={buttonSx} onClick={() => navigate('/')}>Home</Button>
                <Button variant="contained" sx={buttonSx} onClick={() => navigate('/Stations')}>Stations</Button>
                <Button variant="contained" sx={buttonSx} onClick={() => navigate('/Commodities')}>Commodities</Button>
                <Button variant="contained" sx={buttonSx} onClick={() => navigate('/Find_Commodity')}>Find Commodity</Button>
            </AppBar>
            <Toolbar />
        </Box>
    )
}

export default Header