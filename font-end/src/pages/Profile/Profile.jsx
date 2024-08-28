import { Avatar, Box, Button, Card, Tab, Tabs } from '@mui/material';
import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import PostCard from '../../components/Post/PostCard';
import UserReelsCard from '../../components/Reels/UserReelsCard';
import { useSelector } from 'react-redux';
import ProfileModal from './ProfileModal';
const tabs = [
    { value: "post", name: "Post" },
    { value: "reels", name: "Reels" },
    { value: "saved", name: "Saved" },
    { value: "report", name: "Report" },
]
const posts = [1, 1, 1, 1, 1];
const reels = [1, 11, 1, 1, 1, 1];
const savedPosts = [1, 1, 22, 2, 1];
const reports = [1, 11, 2, 2, 34];

const Profile = () => {
    const { id } = useParams();
    const { auth } = useSelector(store => store);
    const [value, setValue] = useState('post');

    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };
    return (
        <Card className='my-10 w-[70%]'>
            <div className='rounded-md'>
                <div className='h-[15rem]'>
                    <img className='w-full h-full rounded-t-md'
                        src='https://static1.makeuseofimages.com/wordpress/wp-content/uploads/2024/01/midjourney-desktop-background-of-a-cottage-by-the-sea.jpg?q=50&fit=crop&w=1100&h=618&dpr=1.5'
                        alt='Man avatar' />
                </div>
                <div className='px-5 flex justify-between items-start mt-5 h-[5rem]' >
                    <Avatar className='transform -translate-y-24'
                        sx={{ width: "10rem", height: "10rem" }} src='' alt='' />
                    {true ? <Button sx={{ borderRadius: "20px" }} variant='outlined' onClick={(handleOpen)}>Edit Profile</Button> : <Button sx={{ borderRadius: "20px" }} variant='outlined'>Follow Button</Button>}

                </div>
                <div className='p-5'>
                    <div>
                        <h1 className='py-1 font-bold text-xl'>{auth?.user?.firstName + " " + auth?.user?.lastName}</h1>
                        <p>@{auth?.user?.firstName?.toLowerCase() + "_" + auth?.user?.lastName?.toLowerCase()}</p>
                    </div>

                    <div className='flex gap-2 items-center py-3'>
                        <span>41 post</span>
                        <span>35 follower</span>
                        <span>5 following</span>
                    </div>

                    <div>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit, nemo iste autem
                            praesentium deleniti incidunt odit obcaecati reiciendis asperiores provident
                            quisquam ipsam exercitationem minus esse molestiae ratione quas quia vero.</p>
                    </div>
                </div>
                <section>
                    <Box sx={{ width: '100%', borderBottom: 1, borderColor: "divider" }}>
                        <Tabs
                            value={value}
                            onChange={handleChange}
                            aria-label="wrapped label tabs example"
                        >
                            {tabs.map(item => <Tab value={item.value}
                                label={item.name} wrapped />)}
                        </Tabs>
                    </Box>
                    <div className='flex justify-center'>
                        {value === "post"
                            ? (<div className='space-y-5 w-[70%] my-10'>
                                {posts.map(item => <div className='border border-slate-100 rounded-md'>
                                    <PostCard /> </div>)}
                            </div>) : value === "reels" ? (<div className='flex flex-wrap gap-2 justify-center'>
                                {reels.map(item => <UserReelsCard />)}
                            </div>) : value === "saved" ? (<div className='space-y-5 w-[70%] my-10'>
                                {savedPosts.map(item => <div className='border border-slate-100 rounded-md'>
                                    <PostCard />
                                </div>)}
                            </div>) : (<div>Reports</div>)}
                    </div>
                </section>
            </div>
            <section>
                <ProfileModal open={open} handleClose={handleClose} handleOpen={handleOpen} />
            </section>

        </Card>
    )
}

export default Profile