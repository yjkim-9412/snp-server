import React, {useState} from "react";
import {Container} from "@mui/material/";
import Header from "../main/Header";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Grid2 from "@mui/material/Unstable_Grid2";
import CalendarMain from "../today/CalendarMain";
import {Box} from "@mui/material";



interface ChildLogin {
    setIsLoggedIn:  React.Dispatch<React.SetStateAction<Boolean>>
}
const theme = createTheme();

const Home:React.FC = () => {
return(
    <ThemeProvider theme={theme}>
    <Container component="main" maxWidth="xl">
        <Box>
            <Grid2 container  display="flex">
                <Header/>
            </Grid2>
            <Grid2 container  display="flex">
                <CalendarMain/>
            </Grid2>
        </Box>
        <Box>
        </Box>
    </Container>
    </ThemeProvider>
)
}
export default Home;