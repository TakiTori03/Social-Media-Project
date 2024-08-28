import { Card, Grid } from '@mui/material'
import React from 'react'
import Login from './Login'
import Register from './Register'

const Authentication = () => {
    return (
        <>
            <Grid container className='h-full' >
                <Grid className='h-screen overflow-hidden' item xs={7}>
                    <img className='w-full h-full object-center ' src="https://cdn.pixabay.com/photo/2018/11/29/21/51/social-media-3846597_1280.png"></img>
                </Grid>
                <Grid item xs={5}>

                    <div className='px-20 flex flex-col justify-center h-full'>
                        <Card className='card p-8'>
                            <div className='flex flex-col items-center mb-5 space-y-1'>
                                <h1 className='logo text-center'>Social Media</h1>
                                <p className='text-center text-sm w-[70&]'> Conect Lives, Sharing and Chatting</p>
                            </div>
                            {/* <Login /> */}
                            <Register />
                        </Card>

                    </div>
                </Grid>
            </Grid>
        </>
    )
}

export default Authentication