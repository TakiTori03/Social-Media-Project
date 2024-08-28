import { Avatar } from '@mui/material'
import React from 'react'

const StoryCicrle = () => {
    return (
        <div>
            <div className='flex flex-col items-center mr-4 cursor-pointer'>
                <Avatar
                    sx={{ width: "5rem", height: "5rem" }}
                    src=''>
                </Avatar>
                <p>Codewith...</p>
            </div>
        </div>
    )
}

export default StoryCicrle