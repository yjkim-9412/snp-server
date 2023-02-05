import React, {useEffect, useState} from 'react';
import parse from 'autosuggest-highlight/parse';
import match from 'autosuggest-highlight/match';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { Autocomplete } from '@mui/material'
import {TextField} from "@mui/material/";
import PropsAction from "../../interface/PropsAction";


type onChangeParent = {
    onChangeValue: (name: string, value: string) => void
    getCategory:string
    getCategoryId:string
}
type CategoryType = {
    id: string, name: string
}[];
const CategoryList: React.FC<onChangeParent> = ({onChangeValue,getCategory,getCategoryId}) => {
    const [categoryMap, setCategoryMap] = useState<CategoryType>([]);
    const [categoryValue, setCategoryValue] = useState({id:'1', name:""});
    const navigate = useNavigate();
    const onChange = (e: string) => {
        onChangeValue('categoryName', e);
        setCategoryValue({...categoryValue,name: e});
    }
    useEffect(() => {
        setCategoryValue((prevState) => ({
            ...prevState,name:getCategory, id: getCategoryId
        }));
    },[getCategory])

    useEffect(() => {
        axios.get('/api/textbooks/categories')
            .then(res => {
                setCategoryMap(res.data);
            })
            .catch(error => {
                if (error.response.status === 401) {
                    navigate('/login');
                } else {
                    console.log(error);
                }
            })
    }, [])
    return (
        <>
            <Autocomplete
                disablePortal
                id="categoryId"
                options={categoryMap}
                isOptionEqualToValue={(option,value) => option.id === value.id}
                value={categoryValue}
                defaultValue={{id:'1',name:"SF명작"}}
                onInputChange={(event, newInputValue) => {
                    onChange(newInputValue);
                }}
                renderOption={(props, categoryMap, {inputValue}) => {
                    const matches = match(categoryMap.name, inputValue, {insideWords: true});
                    const parts = parse(categoryMap.name, matches);

                    return (
                        <li {...props} >
                            <div>
                                {parts.map((part, index) => (
                                    <span
                                        key={index}
                                        style={{
                                            fontWeight: part.highlight ? 700 : 400,
                                        }}
                                    >
                  {part.text}
                </span>
                                ))}
                            </div>
                        </li>
                    );
                }}
                getOptionLabel={(categoryMap) => categoryMap.name}
                sx={{width: 'flex'}}
                renderInput={(params) =>
                    (<TextField {...params}  variant="outlined" fullWidth label="장르"/>)}
            />
        </>
    )
}
export default CategoryList;