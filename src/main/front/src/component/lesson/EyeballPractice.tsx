import React, {useEffect, useState} from 'react'
import {Paper, styled} from "@mui/material";
import TextField from "@mui/material/TextField";
import PropsAction from "../../interface/PropsAction";
import Typography from "@mui/material/Typography";
import {FormHelperText, Grid} from "@mui/material/";

const TextFields = styled(TextField)`
   input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    .MuiFormHelperText-root {
  color: #FF0000 !important;
}
`;
type ConcentrationFieldType = {

    getRapidEyeball?: string,
    getEyeBallCount?: string,

    onChange: (name:string, value:string) => void
}
const EyeballPractice:React.FC<ConcentrationFieldType> = ({getRapidEyeball,
                                                              getEyeBallCount,onChange}) => {
    const [eyeball, setEyeball] = useState({rapidEyeball:'',eyeBallCount:'' });

    const [errorField, setErrorField] = useState('');

    const onChangeField = (e: PropsAction) => {
        let value = e.target.value

        let name = e.target.name
        if (name === 'rapidEyeball' && value.length > 1) {
            setErrorField('최대 9');
            return
        }
        if (value === ''){
            setErrorField('필수 값');
        }else {
            setErrorField( '');
        }
        setEyeball({...eyeball,[name]:value})
        onChange(name, value);
    }
    useEffect(()=>{
        if (typeof getRapidEyeball === "string") {
            setEyeball({...eyeball,rapidEyeball: getRapidEyeball});
        }
    },[getRapidEyeball]);
    useEffect(()=>{
        if (typeof getEyeBallCount === "string") {
            setEyeball({...eyeball,eyeBallCount: getEyeBallCount});
        }
    },[getEyeBallCount]);
    return(
        <Paper
            sx={{
                display: 'flex',
                flexDirection: 'column',
                p:1
            }}>
            <Grid item xs={12} sx={{marginBottom:1}}>

            <Typography sx={{p:1, fontSize:12,float:'left'}}>안구 훈련</Typography>
            <FormHelperText sx={{color:'red', marginTop:1}}>{errorField}</FormHelperText>
            </Grid>
            <Grid item xs={12} sx={{marginBottom:1}}>
            <TextFields size='small' type='number' name='rapidEyeball' label='전체완료 수' variant="outlined"
                        sx={{width:100,marginLeft:1,marginRight:1 }} error={errorField !== ''}
                        value={eyeball.rapidEyeball} focused onChange={onChangeField}
            />
            <TextFields size='small' name='eyeBallCount' label='중간완료' variant="outlined"
                        sx={{width:100,marginLeft:1,marginRight:1}} error={errorField !== ''}
                        value={eyeball.eyeBallCount} focused onChange={onChangeField}
            />
            </Grid>

        </Paper>
    )
}
export default EyeballPractice;