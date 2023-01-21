import PropsAction from "./PropsAction";
import React, {ReactNode, SyntheticEvent} from 'react';
import {SelectChangeEvent} from "@mui/material";

export interface StudentFieldType {
    onChangeType: (e:React.ChangeEvent<HTMLInputElement> & EventTarget ) => void,
    studentValue: string
    textType: string
    labelType: string
    fieldErrorType: string
}

export interface StudentFieldRadio{
    onChangeRadio: (e:React.MouseEvent<HTMLLabelElement, MouseEvent>, param:string) => void
    studentValue: string
    textType: string
    labelType: string
    fieldErrorType: string
}

export interface StudentFieldAddress{
    onChangeAddress: (e:{city:string, street:string}) => void
    fieldErrorType: string

}
export interface StudentFieldSelect {
    onChangeSelect:(e:{name: string, value: string}) => void
    fieldErrorType: string
}
export interface StudentFieldSkill {
    onChangeType: (e:React.ChangeEvent<HTMLInputElement> & EventTarget ) => void,
    studentSpeed: string
    studentReadLv: string
    studentInt: string
}