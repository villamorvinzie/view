import {
  Box,
  Stack,
  TextField,
  Typography,
  Chip,
  List,
  ListItem,
  ListItemAvatar,
  Avatar,
  ListItemText,
  Divider,
  Paper,
} from "@mui/material";
import { DateCalendar } from "@mui/x-date-pickers/DateCalendar";

import ImageIcon from "@mui/icons-material/Image";
import WorkIcon from "@mui/icons-material/Work";
import BeachAccessIcon from "@mui/icons-material/BeachAccess";
import axios from "axios";
import { useCookies } from "react-cookie";

const Profile = () => {
  const [cookies, setCookies] = useCookies(["access_token"]);

  const handleOnDateChange = (e) => {
    const selectedDate = e.$d;
    const tomorrowDate = new Date();
    selectedDate.setDate(selectedDate.getDate() + 1);
    tomorrowDate.setTime(selectedDate.getTime());
    tomorrowDate.setDate(selectedDate.getDate() + 1);
    const selectedDateISO = selectedDate.toISOString().substring(0, 10);
    const tomorrowDateISO = tomorrowDate.toISOString().substring(0, 10);
    const accessToken = cookies["access_token"];

    axios
      .get(
        `http://localhost:8080/view/api/v1/activities?from=${selectedDateISO}&to=${tomorrowDateISO}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then((data) => console.log(data))
      .catch((err) => console.log(err));
  };
  return (
    <Box sx={{ padding: "2%" }}>
      <Stack
        direction={"row"}
        justifyContent={"flex-start"}
        alignItems={"flex-start"}
        spacing={6}
      >
        <DateCalendar onChange={handleOnDateChange} />
        <Divider orientation="vertical" variant="middle" flexItem />
        <Stack
          direction={"column"}
          justifyContent={"flex-start"}
          alignItems={"flex-start"}
          spacing={2}
        >
          <Typography variant="h6">Activities</Typography>
          <Chip label="09:00 AM" />
          <Paper>
            <List>
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <ImageIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="Adidas shoes 5K" />
              </ListItem>
              <ListItem>
                <ListItemAvatar>
                  <Avatar>
                    <ImageIcon />
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="Competitive Programming" />
              </ListItem>
            </List>
          </Paper>
        </Stack>
      </Stack>
    </Box>
  );
};

export default Profile;
