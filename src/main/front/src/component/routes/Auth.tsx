import React, {ChangeEvent, useState} from 'react';
import "react-bootstrap";
import validator from 'validator';
import {
    Button,
    CssBaseline,
    TextField,
    FormControl,
    FormHelperText,
    Grid,
    Box,
    Typography,
    Container,
} from '@mui/material/';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import 'password-validator';
import {styled} from "@mui/material";
import axios from "axios";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;

interface ChildLogin {
    sendLoginStatus: () => void
}
interface Props extends ChangeEvent<HTMLInputElement>{
    target: HTMLInputElement & EventTarget
}



const Auth: React.FC<ChildLogin> = ({sendLoginStatus}) => {

    const passwordValidator = require('password-validator');
    const schema = new passwordValidator().is().min(8).has().uppercase().has().lowercase().has().digits();

    const theme = createTheme();
    const [emailError, setEmailError] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [pw, setPw] = useState<string>('');
    const [pwError, setPwError] = useState<string>('');
    const [loginError, setLoginError] = useState<string>('');

    const changeEmail = (e: Props) =>{
        setEmail(e.target.value);
        console.log(email);
        if (!validator.isEmail(email)) {
            setEmailError("올바른 형식의 이메일이 아닙니다.")
        }else {setEmailError('');}
        console.log(emailError);
    }
    const changePw = (e: Props) =>{
        setPw(e.target.value);
        console.log(pw);
        if (!schema.validate(pw)) {
            setPwError("올바른 형식의 비밀번호가 아닙니다.")
        }else {setPwError('');}
        console.log(pwError);
    }

    const onSubmit = (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        axios.post('/api/login', null, {params: [email, pw]}).then(res => {
            if (res.status === 200 && res.data.token) {
                localStorage.setItem("loginTeacher", res.data.token);
            } else {
                console.log("login Fail");
                setLoginError("비밀번호 또는 이메일주소가 맞지 않습니다.");
            }
        }).catch(error => console.log(error));
    }


    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 30,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Typography component="h1" variant="h5">
                        로그인
                    </Typography>
                    <Box component="form" noValidate  sx={{ mt: 3 }}>
                        <FormControl component="fieldset" variant="standard">
                            <Grid container spacing={2}>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        autoFocus
                                        fullWidth
                                        type="email"
                                        id="email"
                                        name="email"
                                        label="이메일 주소"
                                        value={email}
                                        onChange={changeEmail}
                                        error={emailError!== ''}
                                    />
                                    <FormHelperTexts>{emailError}</FormHelperTexts>
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        fullWidth
                                        type="password"
                                        id="password"
                                        name="password"
                                        label="비밀번호 (숫자+영대소문자+특수문자 8자리 이상)"
                                        value={pw}
                                        onChange={changePw}
                                        error={pwError!== ''}
                                    />
                                    <FormHelperTexts>{pwError}</FormHelperTexts>
                                </Grid>
                            </Grid>
                            <Button
                                onClick={sendLoginStatus}
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                                size="large"
                            >
                                로그인
                            </Button>
                        </FormControl>
                        <FormHelperTexts>{loginError}</FormHelperTexts>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    )
};
export default Auth;