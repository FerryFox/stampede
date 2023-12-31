import {Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, styled} from "@mui/material";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import DashboardCustomizeIcon from "@mui/icons-material/DashboardCustomize";
import Drawer from "@mui/material/Drawer";
import * as React from "react";
import AddCardIcon from '@mui/icons-material/AddCard';
import ApprovalIcon from '@mui/icons-material/Approval';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import Diversity3Icon from '@mui/icons-material/Diversity3';
import MessageIcon from '@mui/icons-material/Message';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ArchiveIcon from '@mui/icons-material/Archive';

const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));

type DrawerComponentProps = {
    open: boolean,
    toggleDrawer: () => void,
    navigate: (path: string) => void,
}

export default function DrawerComponent({ open, toggleDrawer, navigate} : DrawerComponentProps)
{
    const drawerWidth = 240;

return (
    <Drawer onClose={toggleDrawer}
    sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
            width: drawerWidth,
            boxSizing: 'border-box',
        },}} variant="temporary" anchor="left" open={open}>

        <DrawerHeader>
            <IconButton onClick={toggleDrawer}><ChevronLeftIcon />
            </IconButton>
        </DrawerHeader>

        <Divider style={{ height: '5px',  }}/>

        <List>
            <ListItem key={'Dashboard'} disablePadding onClick={() => navigate('/dashboard')}>
                <ListItemButton>
                    <ListItemIcon>
                        <DashboardIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Dashboard'} secondary={'Search for stamp cards'} />
                </ListItemButton>
            </ListItem>
        </List>

        <List>
            <ListItem key={'Templates'} disablePadding onClick={() => navigate('/templates')}>
                <ListItemButton>
                    <ListItemIcon>
                        <DashboardCustomizeIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Public'} secondary={'Search for stamp cards'} />
                </ListItemButton>
            </ListItem>
        </List>

        <Divider style={{ height: '5px',  }} />

        <List>
            <ListItem key={'Templates'} disablePadding onClick={() => navigate('/templates/owned')}>
                <ListItemButton>
                    <ListItemIcon>
                        <AddCardIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Templates'} secondary={'Manage Templates'} />
                </ListItemButton>
            </ListItem>
        </List>

        <List>
            <ListItem key={'Templates'} disablePadding onClick={() => navigate('/stampcards')}>
                <ListItemButton>
                    <ListItemIcon>
                        <ApprovalIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Stamp Cards'} secondary={'Visit your Collection'} />
                </ListItemButton>
            </ListItem>
        </List>

        <List>
            <ListItem key={'Archive'} disablePadding onClick={() => navigate('/stampcards/archive')}>
                <ListItemButton>
                    <ListItemIcon>
                        <ArchiveIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Archive'} secondary={'Visit finished Redeemed Cards'} />
                </ListItemButton>
            </ListItem>
        </List>

        <Divider style={{ height: '5px',  }} />

        <List>
            <ListItem key={'Profile'} disablePadding onClick={() => navigate('/profile')}>
                <ListItemButton>
                    <ListItemIcon>
                        <AccountBoxIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Profile'} secondary={''} />
                </ListItemButton>
            </ListItem>
        </List>

        <List>
            <ListItem key={'FriendsPage'} disablePadding onClick={() => navigate('/friends')}>
                <ListItemButton>
                    <ListItemIcon>
                        <Diversity3Icon />
                    </ListItemIcon>
                    <ListItemText primary={'FriendsPage'} secondary={''} />
                </ListItemButton>
            </ListItem>
        </List>
        <List>
            <ListItem key={'Story'} disablePadding onClick={() => navigate('/blog')}>
                <ListItemButton>
                    <ListItemIcon>
                        <MessageIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Story'} secondary={''} />
                </ListItemButton>
            </ListItem>
        </List>

        <Divider style={{ height: '5px',  }} />
        <List>
            <ListItem key={'Templates'} disablePadding onClick={() => navigate('/')}>
                <ListItemButton>
                    <ListItemIcon>
                        <ArrowBackIosIcon />
                    </ListItemIcon>
                    <ListItemText primary={'Home'} secondary={''} />
                </ListItemButton>
            </ListItem>
        </List>
    </Drawer>
    );
}
