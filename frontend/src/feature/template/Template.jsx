import React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {createStampCardFromTemplateId} from "../../assets/service/stampCardService";

function Template({ TemplateModel })
{

    return (
        <Card sx={{
            p: 0,
            borderRadius: 2,
            minWidth:"50%",
            height: '25vh',}}>
            <CardMedia
                sx={{ height: "15vh" }}
                image={`data:image/jpeg;base64,${TemplateModel.image}`}
                title="green iguana"
            />

            <CardContent sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                <Typography gutterBottom variant="body2" component="div"  sx={{
                    fontSize: {
                        xs: '0.75rem', // Adjust as needed
                        sm: '0.5rem',},
                    textAlign: 'left',
                }} >
                    {TemplateModel.name}
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{
                    fontSize: {
                        xs: '0.5rem', // Adjust as needed
                        sm: '0.4rem',},
                    textAlign: 'left',
                }} >
                    {TemplateModel.description}

                </Typography>
                <Typography variant="body2" color="text.secondary" sx={{ mt :1,
                    fontSize: {
                        xs: '0.5rem', // Adjust as needed
                        sm: '0.4rem',},
                    textAlign: 'left',
                }} >
                    {TemplateModel.stampCardCategory}
                </Typography>
            </CardContent>

            <CardActions>
                <Button onClick={() => {createStampCardFromTemplateId(template.id)}}
                    variant={"contained"}
                    size="small">
                    Get this Card
                </Button>
            </CardActions>
        </Card>
    );
}

export default Template;